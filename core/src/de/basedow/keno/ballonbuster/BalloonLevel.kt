package de.basedow.keno.ballonbuster

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label

class BalloonLevel(game: Game) : BaseScreen(game) {

    val mapWidth = 640f
    val mapHeight = 480f

    private val background = BaseActor()

    private var spawnTimer = 0f
    private var spawnInterval = 0.5f

    private var popped = 0
    private var escaped = 0
    private var clickCount = 0

    private val poppedLabel: Label
    private val escapedLabel: Label
    private val hitRatioLabel: Label

    private var gameOver = false
    private val gameOverLabel: Label

    init {
        background.texture = Texture("sky.jpg")
        background.setPosition(0f, 0f)
        mainStage.addActor(background)

        val style = Label.LabelStyle(BitmapFont(), Color.NAVY)

        poppedLabel = Label("Popped: 0", style)
        poppedLabel.setFontScale(2f)
        poppedLabel.setPosition(20f, 440f)
        uiStage.addActor(poppedLabel)

        escapedLabel = Label("Escaped: 0", style)
        escapedLabel.setFontScale(2f)
        escapedLabel.setPosition(220f, 440f)
        uiStage.addActor(escapedLabel)

        hitRatioLabel = Label("Hit Ratio: ---", style)
        hitRatioLabel.setFontScale(2f)
        hitRatioLabel.setPosition(420f, 440f)
        uiStage.addActor(hitRatioLabel)

        gameOverLabel = Label("Game Over", style)
        gameOverLabel.setFontScale(4f)
        gameOverLabel.setPosition(180f, 250f)
        gameOverLabel.isVisible = false;
        uiStage.addActor(gameOverLabel)
    }

    override fun update(delta: Float) {
        spawnTimer += delta
        if (spawnTimer > spawnInterval) {
            spawnTimer -= spawnInterval

            val ballon = Balloon()
            ballon.addListener(object : InputListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    popped++
                    ballon.addAction(Actions.sequence(
                            Actions.fadeOut(0.5f),
                            Actions.removeActor()
                    ))
                    return true
                }
            })
            mainStage.addActor(ballon)
        }

        for (a in mainStage.actors) {
            if (a.x > mapWidth || a.y > mapHeight) {
                escaped++
                a.remove()
            }
        }

        if (escaped >= 100) {
            gameOver = true;
            isPaused = true;
            gameOverLabel.addAction(Actions.parallel(
                    Actions.alpha(0f),
                    Actions.show(),
                    Actions.fadeIn(2f)
            ))
        }

        poppedLabel.setText("Popped $popped")
        escapedLabel.setText("Escaped: $escaped")
        if (clickCount > 0) {
            val percent = 100 * popped / clickCount
            hitRatioLabel.setText("Hit Ratio: $percent%")
        }
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (gameOver) {
            game.screen = BalloonMenu(game)
            return false
        }

        clickCount++
        return false
    }
}