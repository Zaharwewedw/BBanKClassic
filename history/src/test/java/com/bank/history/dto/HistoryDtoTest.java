package com.bank.history.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryDtoTest {
    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        Long transferAuditId = 2L;
        Long profileAuditId = 3L;
        Long accountAuditId = 4L;
        Long antiFraudAuditId = 5L;
        Long publicBankInfoAuditId = 6L;
        Long authorizationAuditId = 7L;

        HistoryDto historyDto = new HistoryDto(id, transferAuditId, profileAuditId, accountAuditId,
                antiFraudAuditId, publicBankInfoAuditId, authorizationAuditId);

        assertEquals(id, historyDto.getId());
        assertEquals(transferAuditId, historyDto.getTransfer_audit_id());
        assertEquals(profileAuditId, historyDto.getProfile_audit_id());
        assertEquals(accountAuditId, historyDto.getAccount_audit_id());
        assertEquals(antiFraudAuditId, historyDto.getAnti_fraud_audit_id());
        assertEquals(publicBankInfoAuditId, historyDto.getPublic_bank_info_audit_id());
        assertEquals(authorizationAuditId, historyDto.getAuthorization_audit_id());
    }

    @Test
    void testSetters() {
        Long id = 1L;
        Long transferAuditId = 2L;
        Long profileAuditId = 3L;
        Long accountAuditId = 4L;
        Long antiFraudAuditId = 5L;
        Long publicBankInfoAuditId = 6L;
        Long authorizationAuditId = 7L;

        HistoryDto historyDto = new HistoryDto();

        historyDto.setId(id);
        historyDto.setTransfer_audit_id(transferAuditId);
        historyDto.setProfile_audit_id(profileAuditId);
        historyDto.setAccount_audit_id(accountAuditId);
        historyDto.setAnti_fraud_audit_id(antiFraudAuditId);
        historyDto.setPublic_bank_info_audit_id(publicBankInfoAuditId);
        historyDto.setAuthorization_audit_id(authorizationAuditId);

        assertEquals(id, historyDto.getId());
        assertEquals(transferAuditId, historyDto.getTransfer_audit_id());
        assertEquals(profileAuditId, historyDto.getProfile_audit_id());
        assertEquals(accountAuditId, historyDto.getAccount_audit_id());
        assertEquals(antiFraudAuditId, historyDto.getAnti_fraud_audit_id());
        assertEquals(publicBankInfoAuditId, historyDto.getPublic_bank_info_audit_id());
        assertEquals(authorizationAuditId, historyDto.getAuthorization_audit_id());
    }

}
