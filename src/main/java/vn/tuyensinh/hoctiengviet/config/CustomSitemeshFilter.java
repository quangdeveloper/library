package vn.tuyensinh.hoctiengviet.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.spring.boot.ext.config.ConfigurableSiteMeshFilter;

public class CustomSitemeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/decorators/web.jsp")
                .addDecoratorPath("/admin*", "/decorators/admin.jsp")
                .addDecoratorPath("/web*", "/decorators/web.jsp")
                .addExcludedPath("/login*")
                .addExcludedPath("/api*");
    }

}
