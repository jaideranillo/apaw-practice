package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.MemberTeamEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MemberTeamEntityRepositoryIT {

    @Autowired
    private MemberTeamRepository memberTeamRepository;

    @Test
    void testFindMemberByDni() {
        assertTrue(this.memberTeamRepository.findByDni("20000000C").isPresent());
        MemberTeamEntity memberTeam = this.memberTeamRepository.findByDni("20000000C").get();
        assertEquals("Leonor", memberTeam.getName());
        assertEquals("Rodriguez", memberTeam.getSurname());
        assertFalse(memberTeam.getAvailable());
        assertNotNull(memberTeam.getId());
        assertEquals("20000000C", memberTeam.getDni());
    }

    @Test
    void testMemberTeamBuilder() {
        MemberTeamEntity memberTeamEntity = MemberTeamEntity.builder()
                .name("Mark")
                .surname("Gasol")
                .dni("12345678V")
                .available(true)
                .build();

        assertEquals("Mark", memberTeamEntity.getName());
        assertEquals("Gasol", memberTeamEntity.getSurname());
        assertEquals("12345678V", memberTeamEntity.getDni());
        assertTrue(memberTeamEntity.getAvailable());
    }

}
