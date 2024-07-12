package com.tigasinestor.local.model.dto.classbased.email;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {
    private String userReceiver; // Usuario que recibe el correo
    private String subject; // Asunto del correo
    private String message; // Mensaje del correo
}
