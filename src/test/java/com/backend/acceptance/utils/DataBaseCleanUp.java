package com.backend.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Slf4j
@Component
public class DataBaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notAutoGeneratedTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Clean up database");
        // @Entity 가 달린 클래스의 @Table 속성에서 테이블 이름을 파싱
        tableNames = entityManager.getMetamodel().getEntities().stream()
            .filter(type -> type.getJavaType().isAnnotationPresent(Entity.class))
            .map(type -> type.getJavaType().getAnnotation(Table.class).name())
            .toList();
        // id 자동 올림 대상이 아닌 테이블들
        notAutoGeneratedTableNames = List.of("community_user_auth", "community_like_relation",
            "community_user_follow_relation");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("set  referential_integrity false ")
            .executeUpdate(); // 삭제할 때 참조 무결성 제약 조건이 걸리지 않게 해제

        // 테이블 초기화
        for (String tableName : tableNames) {
            entityManager.createNativeQuery("truncate table " + tableName)
                .executeUpdate();

            // auto increment tables id 초기화
            if (!notAutoGeneratedTableNames.contains(tableName)) {
                entityManager.createNativeQuery(
                        "alter table " + tableName + " alter column id restart with 1")
                    .executeUpdate();
            }
        }
        entityManager.createNativeQuery("set  referential_integrity true")
            .executeUpdate(); // 참조 무결성 제약 조건 다시 활성화
    }
}
