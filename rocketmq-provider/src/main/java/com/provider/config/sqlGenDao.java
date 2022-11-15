package com.provider.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author CHOOSE1
 * @date 2022-11-15 10:09:42
 */
public class sqlGenDao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/local?useUnicode=true&rewriteBatchedStatements=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "11098319";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("CHOOSE1") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.ONLY_DATE)
                            // 指定输出目录
                            .outputDir(System.getProperty("user.dir") + "/rocketmq-provider/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> builder.parent("com.provider.table"))
                .strategyConfig(builder -> {
                    builder.addInclude("t_rocketmq_user_info") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "sys_"); // 设置过滤表前缀
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
