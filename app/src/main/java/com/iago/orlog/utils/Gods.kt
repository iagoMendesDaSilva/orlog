package com.iago.orlog.utils

import androidx.compose.runtime.MutableState
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.match.getRandomDiceSides
import kotlin.math.ceil

data class FavorResolution(
    val favor: Favor?,
    val godId: Int?,
)

data class Favor(
    val cost: Int,
    val effect: Float,
    val description: Int,
)

open class God(
    val id: Int,
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
        id = R.string.thor,
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
        id = R.string.idun,
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
        id = R.string.vidar,
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
        id = R.string.ullr,
        img = R.drawable.god_ullr,
        priority = 4,
        name = R.string.god_ullr,
        favors = listOf(
            Favor(2, 2f, R.string.desc_favor_god_ullr),
            Favor(3, 3f, R.string.desc_favor_god_ullr),
            Favor(4, 6f, R.string.desc_favor_god_ullr),
        ),
        useBeforeResolution = true,
        description = R.string.desc_god_ullr
    ),
    God(
        id = R.string.heimdall,
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
        id = R.string.baldr,
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
        id = R.string.brunhild,
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
        id = R.string.freyr,
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
        id = R.string.hel,
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
        id = R.string.skadi,
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
        id = R.string.skuld,
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
        id = R.string.frigg,
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
        id = R.string.loki,
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
        id = R.string.freyja,
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
        id = R.string.mimir,
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
        id = R.string.bragi,
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
        id = R.string.odin,
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
        id = R.string._var,
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
        id = R.string.thrymr,
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
        id = R.string.tyr,
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

class GodFavors {
    companion object {

        const val GEMS = 15

        fun useThorFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            viewModel.updatePlayer("gems", opponent.value.gems - favor.effect.toInt(), opponent)
        }


        fun useThrymrFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val opponentFavorResolution = opponent.value.favorResolution
            val opponentFavorGod = gods.first { it.id == opponentFavorResolution!!.godId }
            val opponentFavorCost =
                opponentFavorGod.favors.filter { it.cost == opponentFavorResolution!!.favor!!.cost }
            val indexFavorCost = opponentFavorGod.favors.indexOf(opponentFavorCost.first())

            var favorResolution = FavorResolution(null, null)

            if (indexFavorCost >= 2)
                favorResolution = FavorResolution(
                    opponentFavorGod.favors[indexFavorCost - 1],
                    opponentFavorGod.id
                )

            viewModel.updatePlayer("favorResolution", favorResolution, opponent)
        }

        fun useVarFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val tokensSpent = opponent.value.favorResolution!!.favor!!.cost
            val gemsGained = tokensSpent * favor.effect.toInt()
            val finalGems = (player.value.gems + gemsGained).coerceAtMost(GEMS)

            viewModel.updatePlayer("gems", finalGems, player)
        }

        fun useOdinFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val gemsSacrificed = (1 until player.value.gems).random()
            val tokensGained = favor.effect.toInt() * gemsSacrificed
            val finalGems = player.value.gems - gemsSacrificed
            val finalTokens = player.value.tokens + tokensGained

            viewModel.updatePlayer("gems", finalGems, player)
            viewModel.updatePlayer("tokens", finalTokens, player)
        }

        fun useBragiFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val hands = dicesSelectedPlayer.value.filter { it.side == DiceFace.HAND }.size
            val tokensGained = favor.effect.toInt() * hands
            val finalTokens = tokensGained + player.value.tokens

            viewModel.updatePlayer("tokens", finalTokens, player)
        }

        fun useFreyjaFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val additionalDices = getRandomDiceSides().take(favor.effect.toInt())

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedPlayer.value)

            dicesUpdated.addAll(additionalDices)
            dicesSelectedPlayer.value = dicesUpdated
        }

        fun useLokiFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val dicesNotBanned = dicesSelectedOpponent.value.subList(
                favor.effect.toInt(),
                dicesSelectedOpponent.value.size
            )
            dicesSelectedOpponent.value = dicesNotBanned
        }

        fun useFriggFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val rerollDices = getRandomDiceSides(dicesSelectedOpponent.value.toMutableList())

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedOpponent.value)

            (0..favor.effect.toInt()).forEachIndexed { index, _ ->
                dicesUpdated[index] = rerollDices[index]
            }

            dicesSelectedOpponent.value = dicesUpdated

        }

        fun useSkuldFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {

            val arrows = dicesSelectedPlayer.value.filter { it.side == DiceFace.ARROW }.size
            val tokensToDestroy = arrows * favor.effect.toInt()
            val finalTokens = (opponent.value.tokens - tokensToDestroy).coerceAtLeast(0)
            viewModel.updatePlayer("tokens", finalTokens, opponent)
        }

        fun useSkadiFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val arrowSide = diceSides.first { it.side === DiceFace.ARROW }
            val arrows = dicesSelectedPlayer.value.filter { it.side == DiceFace.ARROW }.size
            val arrowsToAdd = arrows * favor.effect.toInt()

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedPlayer.value)

            (0..arrowsToAdd).forEach { _ -> dicesUpdated.add(arrowSide) }
            dicesSelectedPlayer.value = dicesUpdated
        }

        fun useMimirFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {

            val playerAttacks = dicesSelectedPlayer.value.filter { it.side in diceFacesAttacks }
            val opponentDefenses =
                dicesSelectedOpponent.value.filter { it.side in diceFacesDefenses }

            var playerDamage = 0
            var opponentDefensesToUse = opponentDefenses.toMutableList()

            playerAttacks.forEach { _ ->
                verifyAttack(opponentDefensesToUse, DiceFace.HELMET) { damage, defensesUpdated ->
                    if (damage)
                        playerDamage += 1
                    opponentDefensesToUse = defensesUpdated
                }
            }

            val tokensGained = playerDamage * favor.effect
            val finalTokens = player.value.tokens + tokensGained

            viewModel.updatePlayer("tokens", finalTokens, player)
        }

        fun useHelFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val playerAttacks = dicesSelectedPlayer.value.filter { it.side in diceFacesAttacks }
            val opponentDefenses =
                dicesSelectedOpponent.value.filter { it.side in diceFacesDefenses }

            var playerDamage = 0
            var opponentDefensesToUse = opponentDefenses.toMutableList()

            playerAttacks.forEach { _ ->
                verifyAttack(opponentDefensesToUse, DiceFace.HELMET) { damage, defensesUpdated ->
                    if (damage)
                        playerDamage += 1
                    opponentDefensesToUse = defensesUpdated
                }
            }

            val gemsGained = playerDamage * favor.effect
            val finalGems = (player.value.gems + gemsGained).toInt().coerceAtMost(GEMS)
            viewModel.updatePlayer("gems", finalGems, player)

        }

        fun useFreyrFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val groups = dicesSelectedPlayer.value.groupBy { it.side }
            val maxGroup = groups.maxByOrNull { it.value.size }
            val diceSideMajority = diceSides.first { it.side === maxGroup?.key }

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedPlayer.value)

            (0..favor.effect.toInt()).forEach { _ -> dicesUpdated.add(diceSideMajority) }
            dicesSelectedPlayer.value = dicesUpdated
        }

        fun useBrunhildFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {

            val axeSide = diceSides.first { it.side === DiceFace.AXE }

            val axes = dicesSelectedPlayer.value.filter { it.side == DiceFace.AXE }.size
            val axes2 = dicesSelectedPlayer.value.filter { it.side == DiceFace.AXE2 }.size

            val totalAxes = axes + axes2
            val axesMultipliedByEffect = totalAxes * ceil(favor.effect).toInt()
            val axesToAdd = totalAxes - axesMultipliedByEffect

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedPlayer.value)

            (0..axesToAdd).forEach { _ -> dicesUpdated.add(axeSide) }
            dicesSelectedPlayer.value = dicesUpdated
        }

        fun useBaldrFavor(
            viewModel: ViewModelOrlog,
            player: MutableState<Player>,
            opponent: MutableState<Player>,
            favor: Favor,
            dicesSelectedPlayer: MutableState<List<DiceSide>>,
            dicesSelectedOpponent: MutableState<List<DiceSide>>
        ) {
            val helmetSide = diceSides.first { it.side === DiceFace.HELMET }
            val shieldSide = diceSides.first { it.side === DiceFace.SHIELD }

            val helmets = dicesSelectedPlayer.value.filter { it.side == DiceFace.HELMET }.size
            val shields = dicesSelectedPlayer.value.filter { it.side == DiceFace.SHIELD }.size

            val helmetsToAdd = helmets * favor.effect.toInt()
            val shieldsToAdd = shields * favor.effect.toInt()

            val dicesUpdated = mutableListOf<DiceSide>()
            dicesUpdated.addAll(dicesSelectedPlayer.value)

            (0..helmetsToAdd).forEach { _ -> dicesUpdated.add(helmetSide) }
            (0..shieldsToAdd).forEach { _ -> dicesUpdated.add(shieldSide) }
            dicesSelectedPlayer.value = dicesUpdated
        }


    fun useHeimdallFavor(
        viewModel: ViewModelOrlog,
        player: MutableState<Player>,
        opponent: MutableState<Player>,
        favor: Favor,
        dicesSelectedPlayer: MutableState<List<DiceSide>>,
        dicesSelectedOpponent: MutableState<List<DiceSide>>
    ) {
        val playerDefenses = dicesSelectedPlayer.value.filter { it.side in diceFacesDefenses }
        val opponentAttacks = dicesSelectedOpponent.value.filter { it.side in diceFacesAttacks }

        var playerBlocks = 0
        var playerDefensesToUse = playerDefenses.toMutableList()

        opponentAttacks.forEach { diceSide ->
            val defenseDiceSide =
                if (diceSide.side === DiceFace.ARROW) DiceFace.SHIELD else DiceFace.HELMET
            verifyBlock(playerDefensesToUse, defenseDiceSide) { block, defensesUpdated ->
                if (block)
                    playerBlocks += 1
                playerDefensesToUse = defensesUpdated
            }
        }

        val gemsGained = playerBlocks * favor.effect
        val finalGems = (player.value.gems + gemsGained).toInt().coerceAtMost(GEMS)
        viewModel.updatePlayer("gems", finalGems, player)

    }

    fun useUllrFavor(
        viewModel: ViewModelOrlog,
        player: MutableState<Player>,
        opponent: MutableState<Player>,
        favor: Favor,
        dicesSelectedPlayer: MutableState<List<DiceSide>>,
        dicesSelectedOpponent: MutableState<List<DiceSide>>
    ) {
        var shieldsRemoved = 0
        dicesSelectedOpponent.value = dicesSelectedOpponent.value.filterNot {
            if (it.side == DiceFace.SHIELD && shieldsRemoved < favor.effect) {
                shieldsRemoved++
                true
            } else false
        }
    }

    fun useVidarFavor(
        viewModel: ViewModelOrlog,
        player: MutableState<Player>,
        opponent: MutableState<Player>,
        favor: Favor,
        dicesSelectedPlayer: MutableState<List<DiceSide>>,
        dicesSelectedOpponent: MutableState<List<DiceSide>>
    ) {
        var helmetsRemoved = 0
        dicesSelectedOpponent.value = dicesSelectedOpponent.value.filterNot {
            if (it.side == DiceFace.HELMET && helmetsRemoved < favor.effect) {
                helmetsRemoved++
                true
            } else false
        }

    }

    fun useIdunFavor(
        viewModel: ViewModelOrlog,
        player: MutableState<Player>,
        opponent: MutableState<Player>,
        favor: Favor,
        dicesSelectedPlayer: MutableState<List<DiceSide>>,
        dicesSelectedOpponent: MutableState<List<DiceSide>>
    ) {

        val finalGems = (player.value.gems + favor.effect).toInt().coerceAtMost(GEMS)
        viewModel.updatePlayer("gems", finalGems, player)
    }

    fun useTyrFavor(
        viewModel: ViewModelOrlog,
        player: MutableState<Player>,
        opponent: MutableState<Player>,
        favor: Favor,
        dicesSelectedPlayer: MutableState<List<DiceSide>>,
        dicesSelectedOpponent: MutableState<List<DiceSide>>
    ) {
        val gemsSacrificed = (1 until player.value.gems).random()
        val finalGems = player.value.gems - gemsSacrificed
        val tokenLost = favor.effect * gemsSacrificed
        val finalTokens = (opponent.value.tokens - tokenLost).toInt().coerceAtLeast(0)

        viewModel.updatePlayer("gems", finalGems, player)
        viewModel.updatePlayer("tokens", finalTokens, opponent)
    }
        fun verifyBlock(
            opponentDefenses: MutableList<DiceSide>,
            diceFace: DiceFace,
            callback: (block: Boolean, opponentDefenses: MutableList<DiceSide>) -> Unit
        ) {
            var block = false
            if (opponentDefenses.any { it.side == diceFace }) {
                val index = opponentDefenses.indexOfFirst { it.side === diceFace }
                opponentDefenses.removeAt(index)
                block = true
            }
            callback(block, opponentDefenses)
        }

        fun verifyAttack(
            opponentDefenses: MutableList<DiceSide>,
            diceFace: DiceFace,
            onAttack: (damage: Boolean, opponentDefenses: MutableList<DiceSide>) -> Unit
        ) {
            var damage = false
            if (opponentDefenses.any { it.side == diceFace }) {
                val index = opponentDefenses.indexOfFirst { it.side === diceFace }
                opponentDefenses.removeAt(index)
            } else
                damage = true
            onAttack(damage, opponentDefenses)
        }
    }
}