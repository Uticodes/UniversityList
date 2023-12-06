tasks.register<Copy>("installGitHook") {
    from(File(rootDir, "githooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks"))
    fileMode = 0b111_111_111 // 0777 in octal notation
}

afterEvaluate {
    tasks.getByPath(":app:preBuild").dependsOn(tasks.named("installGitHook"))
}