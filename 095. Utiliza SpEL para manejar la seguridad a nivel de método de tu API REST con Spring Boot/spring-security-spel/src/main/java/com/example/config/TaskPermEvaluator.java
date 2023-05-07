package com.example.config;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
public class TaskPermEvaluator implements PermissionEvaluator {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object target,
                                 Object permission) {
        Task task = (Task) target;

        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(permission));

        return admin || task.getOwner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {

        Long id = (Long) targetId;

        Optional<Task> taskOpt = taskRepository.findById(id);
        if(taskOpt.isEmpty())
            return false;

        Task task = taskOpt.get();
        String permissionStr = (String) permission;

        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(permissionStr));

        return admin || task.getOwner().equals(authentication.getName());
    }
}
