package com.iago.orlog.utils

import com.iago.orlog.R

data class Effect(
    val cost: Int,
    val value: Float,
)

open class God(
    val img: Int,
    val name: Int,
    val priority: Int,
    val costs: List<Int>,
    val effects: List<Float>,
    val instruction: Int,
    val useBeforeResolution: Boolean,
) {
    fun useEffect(index: Int): Effect {
        return Effect(
            cost = costs[index],
            value = effects[index]
        )
    }
}

val gods = listOf<God>(
    God(
        img = R.drawable.god_thor,
        priority = 6,
        name = R.string.god_thor,
        costs = listOf(4, 8, 12),
        effects = listOf(2f, 5f, 8f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_thor
    ),
    God(
        img = R.drawable.god_idun,
        priority = 7,
        name = R.string.god_idun,
        costs = listOf(4, 7, 10),
        effects = listOf(2f, 4f, 6f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_idun
    ),
    God(
        img = R.drawable.god_vidar,
        priority = 4,
        name = R.string.god_vidar,
        costs = listOf(2, 4, 6),
        effects = listOf(2f, 4f, 6f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_vidar
    ),
    God(
        img = R.drawable.god_ullr,
        priority = 4,
        name = R.string.god_ullr,
        costs = listOf(2, 3, 4),
        effects = listOf(2f, 3f, 6f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_ullr
    ),
    God(
        img = R.drawable.god_heimdall,
        priority = 4,
        name = R.string.god_heimdall,
        costs = listOf(4, 7, 10),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_heimdall
    ),
    God(
        img = R.drawable.god_baldr,
        priority = 4,
        name = R.string.god_baldr,
        costs = listOf(3, 6, 9),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_baldr
    ),
    God(
        img = R.drawable.god_brunhild,
        priority = 4,
        name = R.string.god_brunhild,
        costs = listOf(6, 10, 18),
        effects = listOf(1.5f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_brunhild
    ),
    God(
        img = R.drawable.god_freyr,
        priority = 4,
        name = R.string.god_freyr,
        costs = listOf(4, 6, 8),
        effects = listOf(2f, 3f, 4f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_freyr
    ),
    God(
        img = R.drawable.god_hel,
        priority = 4,
        name = R.string.god_hel,
        costs = listOf(6, 12, 18),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_hel
    ),
    God(
        img = R.drawable.god_skadi,
        priority = 4,
        name = R.string.god_skadi,
        costs = listOf(6, 10, 14),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_skadi
    ),
    God(
        img = R.drawable.god_skuld,
        priority = 3,
        name = R.string.god_skuld,
        costs = listOf(4, 6, 8),
        effects = listOf(2f, 3f, 4f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_skuld
    ),
    God(
        img = R.drawable.god_frigg,
        priority = 2,
        name = R.string.god_frigg,
        costs = listOf(2, 3, 4),
        effects = listOf(2f, 3f, 4f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_frigg
    ),
    God(
        img = R.drawable.god_loki,
        priority = 2,
        name = R.string.god_loki,
        costs = listOf(3, 6, 9),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_loki
    ),
    God(
        img = R.drawable.god_freyja,
        priority = 2,
        name = R.string.god_freyja,
        costs = listOf(4, 4, 6),
        effects = listOf(1f, 1f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_freyja,
    ),
    God(
        img = R.drawable.god_mimir,
        priority = 4,
        name = R.string.god_mimir,
        costs = listOf(3, 5, 7),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_mimir
    ),
    God(
        img = R.drawable.god_bragi,
        priority = 4,
        name = R.string.god_bragi,
        costs = listOf(4, 8, 12),
        effects = listOf(2f, 3f, 4f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_bragi
    ),
    God(
        img = R.drawable.god_odin,
        priority = 7,
        name = R.string.god_odin,
        costs = listOf(6, 8, 10),
        effects = listOf(3f, 4f, 5f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_odin
    ),
    God(
        img = R.drawable.god_var,
        priority = 1,
        name = R.string.god_var,
        costs = listOf(10, 14, 18),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_var,
    ),
    God(
        img = R.drawable.god_thrymr,
        priority = 1,
        name = R.string.god_thrymr,
        costs = listOf(3, 6, 9),
        effects = listOf(1f, 2f, 3f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_thrymr,
    ),
    God(
        img = R.drawable.god_tyr,
        priority = 3,
        name = R.string.god_tyr,
        costs = listOf(4, 6, 8),
        effects = listOf(2f, 3f, 4f),
        useBeforeResolution = false,
        instruction = R.string.desc_god_tyr
    )
)