/*
 * Copyright 2013 OrgSync
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
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
