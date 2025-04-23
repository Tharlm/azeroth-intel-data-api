package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_mk_leaderboard_group")
@NamedEntityGraph(
        name = "get-mkleaderboard-group-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "mkLeaderboard", subgraph = "mkleaderboard-group-subgraph-mkleaderboard"),
                @NamedAttributeNode(value = "mkLeaderboardMembers"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "mkleaderboard-group-subgraph-mkleaderboard",
                        attributeNodes = {
                                @NamedAttributeNode("mkPeriod"),
                                @NamedAttributeNode("region"),
                                @NamedAttributeNode("mkDungeon")
                        }
                )
        }
)
public class MkLeaderboardGroup {
    @Id
    @Column(name = "id_tec_mk_leaderboard_group", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaderboard_group_seq")
    @SequenceGenerator(name = "leaderboard_group_seq", sequenceName = "tb_mk_leaderboard_group_id_tec_mk_leaderboard_group_seq", allocationSize = 500)
    private Integer idTech;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mk_leaderboard", nullable = false, updatable = false)
    private MkLeaderboard mkLeaderboard;

    @NotNull
    @Column(name = "duration", nullable = false, updatable = false)
    private Integer duration;

    @NotNull
    @Column(name = "ranking", nullable = false, updatable = false)
    private Integer ranking;

    @Column(name = "key_level")
    private Integer keyLevel;

    @Column(name = "dt_completed", updatable = false)
    private Instant dateCompleted;

    @OneToMany(mappedBy = "mkLeaderboardGroup", fetch = FetchType.LAZY)
    private Set<MkLeaderboardMember> mkLeaderboardMembers = new LinkedHashSet<>();

}
