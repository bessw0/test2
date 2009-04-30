/*
 * Copyright 2009 Nuxeo SA <http://nuxeo.com>
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
 *
 * Authors:
 *     Florent Guillaume
 */
package org.apache.chemistry.impl.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.chemistry.Document;
import org.apache.chemistry.ObjectEntry;
import org.apache.chemistry.Folder;
import org.apache.chemistry.type.BaseType;

public class SimpleFolder extends SimpleObject implements Folder {

    public SimpleFolder(SimpleData data, SimpleConnection connection) {
        super(data, connection);
    }

    public List<ObjectEntry> getChildren(BaseType type, String orderBy) {
        // TODO type and orderBy
        Set<String> ids = connection.repository.children.get(getId());
        List<ObjectEntry> children = new ArrayList<ObjectEntry>(ids.size());
        for (String id : ids) {
            SimpleData d = connection.repository.datas.get(id);
            // could build a full Object, but some implementations won't
            children.add(new SimpleObjectEntry(d, connection));
        }
        return children;
    }

    public Document newDocument(String typeId) {
        return connection.newDocument(typeId, this);
    }

    public Folder newFolder(String typeId) {
        return connection.newFolder(typeId, this);
    }

}
