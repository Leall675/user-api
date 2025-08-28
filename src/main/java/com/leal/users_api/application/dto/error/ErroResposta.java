package com.leal.users_api.application.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroResposta {
    private long status;
    private String message;
    private List<ErroCampo> erros;

}
