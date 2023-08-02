package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    HAS_WRENCH,
    INVINCIBLE,
    IS_A_WRENCH,
    RESET,
    CAN_RESET,
    CONSUMED_POWER_STAR,
    CONSUMED_SNEAKY_BERRY,
    FERTILE,
    DORMANT,
    IS_PLAYER,
    CONSUMED_POWER_WATER, BOTTLE, POWER_WATER, HEALING_WATER, IS_BOTTLE, HAS_BOTTLE, IS_ENEMY, IS_KOOPA,
    ENEMY_DRINK,
    IS_BOWSER,
    IS_FLYING_KOOPA,
    HAS_KEY,
    AT_LAVA, IS_A_KEY, LOCKED, IS_GHOST, CURSED, ATTACKED_BY_CURSE, INVISIBILITY
}
