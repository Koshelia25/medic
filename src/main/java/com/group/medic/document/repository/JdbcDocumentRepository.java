package com.group.medic.document.repository;

import com.group.medic.document.model.Document;
import com.group.medic.document.model.DocumentStatus;
import com.group.medic.document.model.DocumentStatusPerUser;
import com.group.medic.document.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcDocumentRepository implements DocumentRepository {

    private static final BeanPropertyRowMapper<Document> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(Document.class);

    private static final BeanPropertyRowMapper<DocumentStatusPerUser> STATUS_PER_USER_ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(DocumentStatusPerUser.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDocument;

    private final SimpleJdbcInsert insertUserDocument;

    @Autowired
    public JdbcDocumentRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.insertDocument = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("document")
                .usingGeneratedKeyColumns("id");

        this.insertUserDocument = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user_document")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query("SELECT * FROM document ORDER BY title", ROW_MAPPER);
    }

    @Override
    public Document get(int id) {
        List<Document> documents = jdbcTemplate.query("SELECT * FROM document WHERE id=?",
                ROW_MAPPER, (Integer) id);
        return DataAccessUtils.singleResult(documents);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM document WHERE id=?", (Integer) id);
    }

    @Override
    public Document add(Document document) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", document.getId())
                .addValue("title", document.getTitle());

        Number newKey = insertDocument.executeAndReturnKey(map);
        document.setId(newKey.intValue());
        return document;
    }

    //TODO
    @Override
    public boolean update(Document document) {
        return false;
    }

    @Override
    public List<Document> getAll(int userId) {
        return jdbcTemplate.query("SELECT d.*, ud.document_status FROM document d join user_document ud on d.id = ud.document_id " +
                "where ud.user_id = ? ORDER BY title", ROW_MAPPER, userId);
    }

    @Override
    public List<DocumentStatusPerUser> getAllStatuses(int id) {
        return jdbcTemplate.query("SELECT user_id, document_status FROM user_document where document_id = ? ",
                STATUS_PER_USER_ROW_MAPPER, id);
    }

    @Override
    public boolean updateStatus(Integer id, Integer userId, DocumentStatus status) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("document_id", id)
                .addValue("user_id", userId)
                .addValue("document_status", status.toString());

        return 1 == namedParameterJdbcTemplate.update("update user_document set document_status = :document_status " +
                "where document_id = :document_id AND user_id = :user_id", map);
    }

    @Override
    public UserDocument addUserDocument(UserDocument userDocument) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", userDocument.getId())
                .addValue("user_id", userDocument.getUserId())
                .addValue("document_id", userDocument.getDocumentId())
                .addValue("document_status", userDocument.getDocumentStatus());

        Number newKey = insertUserDocument.executeAndReturnKey(map);
        userDocument.setId(newKey.intValue());
        return userDocument;
    }
}
