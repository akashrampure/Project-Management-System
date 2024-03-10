package com.project.main.validation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Component;

import com.project.main.model.Project;

@Component
public class ProjectValidator implements Validator{

	@Override
    public boolean supports(Class<?> clazz) {
        return Project.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;

        if (project == null) {
            errors.reject("project.null", "Project object is null");
            return;
        }

        if (project.getId() == null) {
            if (StringUtils.isBlank(project.getName())) {
                errors.rejectValue("name", "field.required", "Name is required");
            }
            if (StringUtils.isBlank(project.getDescription())) {
                errors.rejectValue("description", "field.required", "Description is required");
            }
        } else {
            if (StringUtils.isBlank(project.getName()) && StringUtils.isBlank(project.getDescription())) {
                errors.reject("update.nochange", "At least one field should be modified for update");
            }
        }
    }
}
