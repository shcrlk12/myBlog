package com.kjwon.myblog.admin.repository;

import com.kjwon.myblog.admin.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, String> {
}
