/*
 * Copyright 2013 Orient Technologies.
 * Copyright 2013 Geomatys.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientechnologies.orient.core.sql.method.misc;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.sql.method.OSQLMethod;
import com.orientechnologies.orient.core.sql.model.OExpression;

/**
 *
 * @author Johann Sorel (Geomatys)
 * @author Luca Garulli
 */
public class OSQLMethodAppend extends OSQLMethod {

  public static final String NAME = "append";

  public OSQLMethodAppend() {
    super(NAME, 1);
  }

  @Override
  protected Object evaluateNow(OCommandContext context, Object candidate) {
    String concat = null;
    for (OExpression exp : children) {
      final Object obj = exp.evaluate(context, candidate);
      if (obj != null) {
        if (concat == null) {
          concat = obj.toString();
        } else {
          concat += obj.toString();
        }
      }
    }
    return concat;
  }

  @Override
  public OSQLMethodAppend copy() {
    final OSQLMethodAppend method = new OSQLMethodAppend();
    method.getArguments().addAll(getArguments());
    return method;
  }
  
}