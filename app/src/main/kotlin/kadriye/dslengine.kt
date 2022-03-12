package dev.kavatkedi.kadriye.dslengine
import kotlin.collections.*
/**
 * A task for Kadriye
 *
 * Represents a Main clone, taking an argument array and returning an exit code
 */

typealias KadriyeTask = (List<String>?) -> Int

/**
 * The Kadriye Task Engine.
 *
 * Represents Kadriye's DSL engine, which is used for declaring tasks
 */
public class KadriyeTaskEngine {
	// internal map to store tasks
        private var tasks = HashMap<String, KadriyeTask>()
	/**
	 * Create a task.
	 *
	 * @param name The name of this task.
	 * @param action The action to do for this task.
	 * @author Yiğit Cemal Öztürk
	 **/
        public fun task(name: String, action: KadriyeTask) {
                this.tasks.put(name, action)
        }
	/**
	 * Run a given task.
	 *
	 * @param name The task to run.
	 * @param args The command line arguments passed to the task.
	 * @return If the task exists, its exit code. Otherwise, null
	 * @author Yiğit Cemal Öztürk
	 */
        public fun run(name: String, args:List<String>?): Int? {
		if(this.tasks[name] == null) {
			println("$name is running...[it does not exist]")
			return null
		}
		println("$name started...\n\r")
                return this.tasks[name]?.invoke(args ?: List<String>(0){""})
        }
        
}

/**
 *
 * Represents a project author.
 *
 * @param name The name of the author.
 * @param mail The e-mail adress of the author.
 * @param github The GitHub account of the author.
 * @author Yiğit Cemal Öztürk
 * */
public data class Author(val name:String, val mail:String, val github: String?)
/**
 *
 * Represents a project.
 *
 * @param name The project's name.
 * @param authors An array of project [Author] objects.
 * @param repo The project's Git repository.
 * @param tags The project's tags.
 * @param kotlinVersion Kotlin version of the project.
 * @param version The project version.
 * @param dependencies The project's dependencies.
 * @author Yiğit Cemal Öztürk
 */
public data class Project(val name:String, val authors:Array<Author>, val repo: String?, val tags:Array<String>?, val kotlinVersion:KotlinVersion, val version: Version, val dependencies: Array<Dependency>)
/**
 *
 * Represents a version that fits the SemVer specification.
 * 
 * @param major Major version number
 * @param minor Minor version number
 * @param build Build number
 * @param edit The edit number. This should be 0.
 * @author Yiğit Cemal Öztürk
 */
public data class Version(val major:Int, val minor: Int, val build:Int, val edit:Int)

/**
 *
 * Represents a dependency 
 *
 * @param name Dependency name
 * @param version Dependency version
 * @author Yiğit Cemal Öztürk
 */
public data class Dependency(val name:String, val version: Version)
/**
 * The main Kadriye engine
 * @author Yiğit Cemal Öztürk
 */
public object Kadriye {
	/**
	 * The project
	 * @author Yiğit Cemal Öztürk
	 */
	public var proj: Project? = null
	/**
	 * The task engine.
	 * @author Yiğit Cemal Öztürk
	 */
	private val engine: KadriyeTaskEngine = KadriyeTaskEngine()
	/**
	 * Create a new task
	 * @param name The task name
	 * @param action The task action
	 * @author Yiğit Cemal Öztürk
	 */
	public fun task(name: String, action: KadriyeTask) {
		engine.task(name, action)
	}

	/**
	 * Run the given task
	 * @param name Task name
	 * @param args Task args
	 * @return The exit status if the task was found, otherwisse, `null`
	 */
	public fun run(name: String, args: List<String>?): Int?{
		return engine.run(name, args)
	}

}
