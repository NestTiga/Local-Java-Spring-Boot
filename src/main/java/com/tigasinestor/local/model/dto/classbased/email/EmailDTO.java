package com.tigasinestor.local.model.dto.classbased.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO {
    private String userReceiver; // Usuario que recibe el correo
    private String subject; // Asunto del correo
    private String message; // Mensaje del correo
}
