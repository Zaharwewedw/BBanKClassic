package com.bank.transfer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Класс, который ответственен за json, который отображается при исклчении
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferIncorrectData {
    private String info;
}
