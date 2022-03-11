package dev.kavatkedi.kadriye.dslengine
import kotlin.collections.HashMap

typealias KadriyeTask = (Array<String>) -> Int
public class KadriyeEngine {
        private var tasks = HashMap<String, KadriyeTask>()
        public fun task(name: String, action: KadriyeTask) {
                this.tasks.put(name, action)
        }
        public fun run(name: String, args:Array<String>): Int? {
		if(this.tasks[name] == null) {
			println("$name is running...[it does not exist]")
			return 1
		}
                return this.tasks[name]?.invoke(args)
        }
}
