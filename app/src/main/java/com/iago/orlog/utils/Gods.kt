package com.iago.orlog.utils

import com.iago.orlog.R

data class Favor(
    val cost: Int,
    val effect: Float,
    val description: Int,
)

open class God(
    val img: Int,
    val name: Int,
    val priority: Int,
    val favors: List<Favor>,
    val description: Int,
    val useBeforeResolution: Boolean,
) {
    fun useEffect(index: Int): Favor {
        return favors[index]
    }
}

val gods = listOf<God>(
    God(
        img = R.drawable.god_thor,
        priority = 6,
        name = R.string.god_thor,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_thor),
            Favor(8, 5f, R.string.desc_favor_god_thor),
            Favor(12, 8f, R.string.desc_favor_god_thor),
        ),
        useBeforeResolution = false,
        description = R.string.desc_god_thor
    ),
    God(
        img = R.drawable.god_idun,
        priority = 7,
        name = R.string.god_idun,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_idun),
            Favor(7, 4f, R.string.desc_favor_god_idun),
            Favor(10, 6f, R.string.desc_favor_god_idun),
        ),
        useBeforeResolution = false,
        description = R.string.desc_god_idun
    ),
    God(
        img = R.drawable.god_vidar,
        priority = 4,
        name = R.string.god_vidar,
        favors = listOf(
            Favor(2, 2f, R.string.desc_favor_god_vidar),
            Favor(4, 4f, R.string.desc_favor_god_vidar),
            Favor(6, 6f, R.string.desc_favor_god_vidar),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_vidar
    ),
    God(
        img = R.drawable.god_ullr,
        priority = 4,
        name = R.string.god_ullr,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_ullr),
            Favor(3, 3f, R.string.desc_favor_god_ullr),
            Favor(4, 6f, R.string.desc_favor_god_ullr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_ullr
    ),
    God(
        img = R.drawable.god_heimdall,
        priority = 4,
        name = R.string.god_heimdall,
        favors = listOf(
            Favor(4, 1f, R.string.desc_favor_god_heimdall),
            Favor(7, 2f, R.string.desc_favor_god_heimdall),
            Favor(10, 3f, R.string.desc_favor_god_heimdall),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_heimdall
    ),
    God(
        img = R.drawable.god_baldr,
        priority = 4,
        name = R.string.god_baldr,
        favors = listOf(
            Favor(3, 1f, R.string.desc_favor_god_baldr),
            Favor(6, 2f, R.string.desc_favor_god_baldr),
            Favor(9, 3f, R.string.desc_favor_god_baldr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_baldr
    ),
    God(
        img = R.drawable.god_brunhild,
        priority = 4,
        name = R.string.god_brunhild,
        favors = listOf(
            Favor(6, 1.5f, R.string.desc_favor_god_brunhild),
            Favor(10, 2f, R.string.desc_favor_god_brunhild),
            Favor(18, 3f, R.string.desc_favor_god_brunhild),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_brunhild
    ),
    God(
        img = R.drawable.god_freyr,
        priority = 4,
        name = R.string.god_freyr,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_freyr),
            Favor(6, 3f, R.string.desc_favor_god_freyr),
            Favor(8, 4f, R.string.desc_favor_god_freyr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_freyr
    ),
    God(
        img = R.drawable.god_hel,
        priority = 4,
        name = R.string.god_hel,
        favors = listOf(
            Favor(6, 1f, R.string.desc_favor_god_hel),
            Favor(12, 2f, R.string.desc_favor_god_hel),
            Favor(18, 3f, R.string.desc_favor_god_hel),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_hel
    ),
    God(
        img = R.drawable.god_skadi,
        priority = 4,
        name = R.string.god_skadi,
        favors = listOf(
            Favor(6, 1f, R.string.desc_favor_god_skadi),
            Favor(10, 2f, R.string.desc_favor_god_skadi),
            Favor(14, 3f, R.string.desc_favor_god_skadi),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_skadi
    ),
    God(
        img = R.drawable.god_skuld,
        priority = 3,
        name = R.string.god_skuld,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_skuld),
            Favor(6, 3f, R.string.desc_favor_god_skuld),
            Favor(8, 4f, R.string.desc_favor_god_skuld),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_skuld
    ),
    God(
        img = R.drawable.god_frigg,
        priority = 2,
        name = R.string.god_frigg,
        favors = listOf(
            Favor(2, 2f, R.string.desc_favor_god_frigg),
            Favor(3, 3f, R.string.desc_favor_god_frigg),
            Favor(4, 4f, R.string.desc_favor_god_frigg),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_frigg
    ),
    God(
        img = R.drawable.god_loki,
        priority = 2,
        name = R.string.god_loki,
        favors = listOf(
            Favor(3, 1f, R.string.desc_favor_god_loki),
            Favor(6, 2f, R.string.desc_favor_god_loki),
            Favor(9, 3f, R.string.desc_favor_god_loki),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_loki
    ),
    God(
        img = R.drawable.god_freyja,
        priority = 2,
        name = R.string.god_freyja,
        favors = listOf(
            Favor(2, 1f, R.string.desc_favor_god_freyja),
            Favor(4, 2f, R.string.desc_favor_god_freyja),
            Favor(6, 3f, R.string.desc_favor_god_freyja),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_freyja,
    ),
    God(
        img = R.drawable.god_mimir,
        priority = 4,
        name = R.string.god_mimir,
        favors = listOf(
            Favor(3, 1f, R.string.desc_favor_god_mimir),
            Favor(5, 2f, R.string.desc_favor_god_mimir),
            Favor(7, 3f, R.string.desc_favor_god_mimir),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_mimir
    ),
    God(
        img = R.drawable.god_bragi,
        priority = 4,
        name = R.string.god_bragi,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_bragi),
            Favor(8, 3f, R.string.desc_favor_god_bragi),
            Favor(12, 4f, R.string.desc_favor_god_bragi),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_bragi
    ),
    God(
        img = R.drawable.god_odin,
        priority = 7,
        name = R.string.god_odin,
        favors = listOf(
            Favor(6, 3f, R.string.desc_favor_god_odin),
            Favor(8, 4f, R.string.desc_favor_god_odin),
            Favor(10, 5f, R.string.desc_favor_god_odin),
        ),
        useBeforeResolution = false,
        description = R.string.desc_god_odin
    ),
    God(
        img = R.drawable.god_var,
        priority = 1,
        name = R.string.god_var,
        favors = listOf(
            Favor(10, 1f, R.string.desc_favor_god_var),
            Favor(14, 2f, R.string.desc_favor_god_var),
            Favor(18, 3f, R.string.desc_favor_god_var),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_var,
    ),
    God(
        img = R.drawable.god_thrymr,
        priority = 1,
        name = R.string.god_thrymr,
        favors = listOf(
            Favor(3, 1f, R.string.desc_favor_god_thrymr),
            Favor(6, 2f, R.string.desc_favor_god_thrymr),
            Favor(9, 3f, R.string.desc_favor_god_thrymr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_thrymr,
    ),
    God(
        img = R.drawable.god_tyr,
        priority = 3,
        name = R.string.god_tyr,
        favors = listOf(
            Favor(4, 2f, R.string.desc_favor_god_tyr),
            Favor(6, 3f, R.string.desc_favor_god_tyr),
            Favor(8, 4f, R.string.desc_favor_god_tyr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_tyr
    )
)