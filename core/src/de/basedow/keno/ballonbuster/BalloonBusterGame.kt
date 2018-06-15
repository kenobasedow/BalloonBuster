package de.basedow.keno.ballonbuster

import com.badlogic.gdx.Game

class BalloonBusterGame : Game() {

    override fun create() {
        screen = BalloonMenu(this)
    }
}
