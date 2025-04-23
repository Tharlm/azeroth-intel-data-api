package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "tb_mk_leaderboard",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tb_mk_leaderboard_id_mk_period_id_mk_dungeon_id_connected_realm",
                        columnNames = {"id_mk_period", "id_mk_dungeon", "id_connected_realm"}
                )
        }
)
@NamedEntityGraph(
        name = "get-mkleaderboar-dentity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "groups", subgraph = "leaderboard-group"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "leaderboard-group",
                        attributeNodes = {
                                @NamedAttributeNode("mkLeaderboardMembers")
                        }
                )
        }
)
public class MkLeaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaderboard_seq")
    @SequenceGenerator(name = "leaderboard_seq", sequenceName = "tb_mk_leaderboard_id_tec_mk_leaderboard_seq", allocationSize = 1)
    @Column(
            name = "id_tec_mk_leaderboard",
            nullable = false,
            updatable = false
    )
    private Integer idTech;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_mk_dungeon",
            nullable = false,
            updatable = false
    )
    private MkDungeon mkDungeon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "id_region", referencedColumnName = "id_region", nullable = false, updatable = false),
            @JoinColumn(name = "id_mk_period", referencedColumnName = "id_mk_period", nullable = false, updatable = false),
    })
    private MkPeriod mkPeriod;

    @JoinColumn(
            name = "id_region",
            nullable = false,
            insertable = false,
            updatable = false
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private RegionDao region;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_connected_realm", nullable = false, updatable = false)
    private ConnectedRealmDao connectedRealm;

    @NotNull
    @Column(name = "id_keystone_affixe_1", nullable = false)
    private Integer idKeystoneAffixe1;

    @NotNull
    @Column(name = "start_level_affixe_1", nullable = false)
    private Integer startLevelAffixe1;

    @NotNull
    @Column(name = "id_keystone_affixe_2", nullable = false)
    private Integer idKeystoneAffixe2;

    @NotNull
    @Column(name = "start_level_affixe_2", nullable = false)
    private Integer startLevelAffixe2;

    @NotNull
    @Column(name = "id_keystone_affixe_3", nullable = false)
    private Integer idKeystoneAffixe3;

    @NotNull
    @Column(name = "start_level_affixe_3", nullable = false)
    private Integer startLevelAffixe3;

    @OneToMany(mappedBy = "mkLeaderboard", fetch = FetchType.LAZY)
    private List<MkLeaderboardGroup> groups = new ArrayList<>();

}
