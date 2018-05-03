package de.basedow.keno.ballonbuster.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import de.basedow.keno.ballonbuster.BalloonBusterGame

fun main(args: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.width = 640
    config.height = 480
    LwjglApplication(BalloonBusterGame(), config)
}
