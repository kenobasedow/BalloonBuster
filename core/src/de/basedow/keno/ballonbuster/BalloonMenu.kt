package de.basedow.keno.ballonbuster

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label

class BalloonMenu(game: Game) : BaseScreen(game) {

    init {
        val background = BaseActor()
        background.texture = Texture("sky.jpg")
        background.setPosition(0f, 0f)
        mainStage.addActor(background)

        val style = Label.LabelStyle(BitmapFont(), Color.NAVY)
        val startGameLabel = Label("Click here to start", style)
        startGameLabel.setFontScale(4f)
        startGameLabel.setPosition(100f, 250f)
        uiStage.addActor(startGameLabel)
    }

    override fun update(delta: Float) {}

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        game.screen = BalloonLevel(game)
        return true
    }
}