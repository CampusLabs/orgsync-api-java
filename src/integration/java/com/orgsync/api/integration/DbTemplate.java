package com.orgsync.api.integration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;

public class DbTemplate {

    public static final Config CONFIG =
            ConfigFactory.load("db_template.conf").resolve();

    public static String getString(final String path) {
        return CONFIG.getString("template.community." + path);
    }

    public static List<? extends Config> getList(final String path) {
        return CONFIG.getConfigList("template.community." + path);
    }

    public static void main(final String[] args) throws IOException {
        File file = new File("build/db_template.json");
        System.out.println("Generating template json to file: "
                + file.getAbsolutePath());

        ConfigRenderOptions renderOptions = ConfigRenderOptions.defaults()
                .setJson(true).setComments(false).setOriginComments(false);

        String json = CONFIG.getValue("template").render(renderOptions);

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(json);

        bufferedWriter.close();
    }

}
