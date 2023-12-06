package dev.fullstackcode.graalvm.configuration;

import dev.fullstackcode.graalvm.service.WelcomeService;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
public class PythonConfiguration {
    ResourceLoader resourceLoader;

    private static String pythonPath = "classpath:dev/fullstackcode/python/service";

    public PythonConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public WelcomeService invokeHelloFunction() throws IOException {
        Context context = Context
                .newBuilder("python")
                .allowAllAccess(true)
                .option("python.ForceImportSite", "true")
                .option("python.PythonPath",
                        resourceLoader
                                .getResource(pythonPath)
                                .getFile()
                                .toPath()
                                .toString()
                )
                .build();

            Source source = Source
                    .newBuilder("python",
                            resourceLoader.getResource(pythonPath + "/hello.py").getFile()
                    ).build();

            context.eval(source);

            return context
                    .getBindings("python")
                    .getMember("hello")
                    .as(WelcomeService.class);
        }
    }
//}
