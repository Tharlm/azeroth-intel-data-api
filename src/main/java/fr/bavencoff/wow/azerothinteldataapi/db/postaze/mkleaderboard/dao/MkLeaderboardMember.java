package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_mk_leaderboard_members")
public class MkLeaderboardMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaderboard_member_seq")
    @SequenceGenerator(name = "leaderboard_member_seq", sequenceName = "tb_mk_leaderboard_members_id_tec_mk_leaderboard_members_seq", allocationSize = 2500)
    @Column(name = "id_tec_mk_leaderboard_members", nullable = false, updatable = false)
    private Integer idTech;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mk_leaderboard_group", nullable = false, updatable = false)
    private MkLeaderboardGroup mkLeaderboardGroup;

    @NotNull
    @Column(name = "id_player", nullable = false, updatable = false)
    private Integer idPlayer;

    @Size(max = 12)
    @NotNull
    @Column(name = "lb_name_player", nullable = false, length = 12, updatable = false)
    private String namePlayer;

    @NotNull
    @Column(name = "id_realm", nullable = false, updatable = false)
    private Integer idRealm;

    @Size(max = 1)
    @NotNull
    @Column(name = "lb_faction", nullable = false, length = 1, updatable = false)
    private String faction;

    @NotNull
    @Column(name = "id_specialization", nullable = false, updatable = false)
    private Integer idSpecialization;

}
