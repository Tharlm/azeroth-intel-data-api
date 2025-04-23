package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "lk_mk_season_mk_dungeon"
)
@NamedEntityGraph(
        name = "get-link-mk-season-mk-dungeon-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "mkSeason", subgraph = "lk_mk_season_mk_dungeon_sub_graph_mk_season"),
                @NamedAttributeNode(value = "mkDungeon", subgraph = "lk_mk_season_mk_dungeon_sub_graph_mk_dungeon")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "lk_mk_season_mk_dungeon_sub_graph_mk_season",
                        attributeNodes = {}
                ),
                @NamedSubgraph(
                        name = "lk_mk_season_mk_dungeon_sub_graph_mk_dungeon",
                        attributeNodes = {}
                )
        }
)
public class LinkMkSeasonMkDungeon {
    @EmbeddedId
    private LinkMkSeasonMkDungeonId id;

    //    @MapsId("idMkSeason")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "id_mk_season",
                    referencedColumnName = "id_mk_season",
                    nullable = false,
                    updatable = false,
                    insertable = false
            ),
            @JoinColumn(
                    name = "id_region",
                    referencedColumnName = "id_region",
                    nullable = false,
                    updatable = false,
                    insertable = false
            )
    })
    private MkSeason mkSeason;

    @MapsId("idMkDungeon")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mk_dungeon", nullable = false, updatable = false, insertable = false)
    private MkDungeon mkDungeon;

    @NotNull
    @Column(name = "id_mk_season", nullable = false, updatable = false, insertable = false)
    private Integer idMkSeason;

    @NotNull
    @Column(name = "id_region", nullable = false, updatable = false, insertable = false)
    private Short idRegion;

}
