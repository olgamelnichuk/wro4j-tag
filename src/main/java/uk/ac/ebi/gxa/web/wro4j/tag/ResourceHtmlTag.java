/*
 * Copyright 2008-2011 Microarray Informatics Team, EMBL-European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * For further details of the Gene Expression Atlas project, including source code,
 * downloads and documentation, please see:
 *
 * http://gxa.github.com/gxa
 */

package uk.ac.ebi.gxa.web.wro4j.tag;

import ro.isdc.wro.model.resource.ResourceType;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Olga Melnichuk
 */
enum ResourceHtmlTag {
    CSS(ResourceType.CSS, "<link type=\"text/css\" rel=\"stylesheet\" href=\"%s\"/>"),
    JS(ResourceType.JS, "<script type=\"text/javascript\" src=\"%s\"></script>");

    private static final Map<ResourceType, ResourceHtmlTag> BY_TYPE = newHashMap();

    private ResourceType type;
    private String format;

    private ResourceHtmlTag(ResourceType type, String format) {
        this.type = type;
        this.format = format;
    }

    public String asString(String src) {
        return String.format(format, src);
    }

    public static ResourceHtmlTag of(ResourceType type) {
        ResourceHtmlTag tag = BY_TYPE.get(type);
        if (tag == null) {
            throw new IllegalStateException("Unsupported resource type: " + type);
        }
        return tag;
    }

    static {
        for (ResourceHtmlTag tag : values()) {
            BY_TYPE.put(tag.type, tag);
        }
    }
}