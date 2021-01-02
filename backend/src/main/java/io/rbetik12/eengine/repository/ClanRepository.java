package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ClanRepository extends JpaRepository<Clan, Long> {

    @Query(value = "select count(*) from create_new_clan(?1, ?2, ?3, ?4)", nativeQuery = true)
    int createClan(@Param("creatorId") int creatorId, @Param("clanName") String clanName,
                    @Param("regionId") int regionId, @Param("clanType") String clanType);

    Clan getByName(String name);
}
