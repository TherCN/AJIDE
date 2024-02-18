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

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The options of a Workspace Symbol Request.
 */
@SuppressWarnings("all")
public class WorkspaceSymbolOptions extends AbstractWorkDoneProgressOptions {
  /**
   * The server provides support to resolve additional
   * information for a workspace symbol.
   * <p>
   * Since 3.17.0
   */
  private Boolean resolveProvider;

  public WorkspaceSymbolOptions() {
  }

  public WorkspaceSymbolOptions(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }

  /**
   * The server provides support to resolve additional
   * information for a workspace symbol.
   * <p>
   * Since 3.17.0
   */
  public Boolean getResolveProvider() {
    return this.resolveProvider;
  }

  /**
   * The server provides support to resolve additional
   * information for a workspace symbol.
   * <p>
   * Since 3.17.0
   */
  public void setResolveProvider(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("resolveProvider", this.resolveProvider);
    b.add("workDoneProgress", getWorkDoneProgress());
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
    if (!super.equals(obj))
      return false;
    WorkspaceSymbolOptions other = (WorkspaceSymbolOptions) obj;
    if (this.resolveProvider == null) {
      if (other.resolveProvider != null)
        return false;
    } else if (!this.resolveProvider.equals(other.resolveProvider))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.resolveProvider== null) ? 0 : this.resolveProvider.hashCode());
  }
}
