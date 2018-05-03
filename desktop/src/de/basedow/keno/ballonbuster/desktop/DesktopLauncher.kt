package de.basedow.keno.ballonbuster.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import de.basedow.keno.ballonbuster.BalloonBusterGame

fun main(args: Array<String>) {
    val config = LwjglApplicationConfiguration()
    LwjglApplication(BalloonBusterGame(), config)
}
