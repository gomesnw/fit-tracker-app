package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.User;

public record UserSummaryDTO(Long id, String name, String username) {

    public UserSummaryDTO (User entity){
        this(entity.getId(), entity.getName(), entity.getUsername());
    }
}



