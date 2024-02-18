/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The client support partial workspace symbols. The client will send the
 * request {@code workspaceSymbol/resolve} to the server to resolve additional
 * properties.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class WorkspaceSymbolResolveSupportCapabilities {
  /**
   * The properties that a client can resolve lazily. Usually
   * {@code location.range}
   */
  @NonNull
  private List<String> properties;

  public WorkspaceSymbolResolveSupportCapabilities() {
    ArrayList<String> _arrayList = new ArrayList<String>();
    this.properties = _arrayList;
  }

  public WorkspaceSymbolResolveSupportCapabilities(@NonNull final List<String> properties) {
    this.properties = Preconditions.<List<String>>checkNotNull(properties, "properties");
  }

  /**
   * The properties that a client can resolve lazily. Usually
   * {@code location.range}
   */
  @NonNull
  public List<String> getProperties() {
    return this.properties;
  }

  /**
   * The properties that a client can resolve lazily. Usually
   * {@code location.range}
   */
  public void setProperties(@NonNull final List<String> properties) {
    this.properties = Preconditions.checkNotNull(properties, "properties");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("properties", this.properties);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    WorkspaceSymbolResolveSupportCapabilities other = (WorkspaceSymbolResolveSupportCapabilities) obj;
    if (this.properties == null) {
      if (other.properties != null)
        return false;
    } else if (!this.properties.equals(other.properties))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.properties== null) ? 0 : this.properties.hashCode());
  }
}
