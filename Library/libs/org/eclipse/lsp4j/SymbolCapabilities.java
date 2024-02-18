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
 * Capabilities specific to the {@code workspace/symbol} request.
 * <p>
 * Referred to in the spec as {@code WorkspaceSymbolClientCapabilities}.
 */
@SuppressWarnings("all")
public class SymbolCapabilities extends DynamicRegistrationCapabilities {
  /**
   * Specific capabilities for the {@link SymbolKind} in the {@code workspace/symbol} request.
   * <p>
   * Since 3.4.0
   */
  private SymbolKindCapabilities symbolKind;

  /**
   * The client supports tags on {@link SymbolInformation} and {@link WorkspaceSymbol}.
   * Clients supporting tags have to handle unknown tags gracefully.
   * <p>
   * Since 3.16.0
   */
  private SymbolTagSupportCapabilities tagSupport;

  /**
   * The client supports partial workspace symbols. The client will send the
   * request {@code workspaceSymbol/resolve} to the server to resolve additional
   * properties.
   * <p>
   * Since 3.17.0
   */
  private WorkspaceSymbolResolveSupportCapabilities resolveSupport;

  public SymbolCapabilities() {
  }

  public SymbolCapabilities(final Boolean dynamicRegistration) {
    super(dynamicRegistration);
  }

  public SymbolCapabilities(final SymbolKindCapabilities symbolKind) {
    this.symbolKind = symbolKind;
  }

  public SymbolCapabilities(final SymbolKindCapabilities symbolKind, final Boolean dynamicRegistration) {
    super(dynamicRegistration);
    this.symbolKind = symbolKind;
  }

  /**
   * Specific capabilities for the {@link SymbolKind} in the {@code workspace/symbol} request.
   * <p>
   * Since 3.4.0
   */
  public SymbolKindCapabilities getSymbolKind() {
    return this.symbolKind;
  }

  /**
   * Specific capabilities for the {@link SymbolKind} in the {@code workspace/symbol} request.
   * <p>
   * Since 3.4.0
   */
  public void setSymbolKind(final SymbolKindCapabilities symbolKind) {
    this.symbolKind = symbolKind;
  }

  /**
   * The client supports tags on {@link SymbolInformation} and {@link WorkspaceSymbol}.
   * Clients supporting tags have to handle unknown tags gracefully.
   * <p>
   * Since 3.16.0
   */
  public SymbolTagSupportCapabilities getTagSupport() {
    return this.tagSupport;
  }

  /**
   * The client supports tags on {@link SymbolInformation} and {@link WorkspaceSymbol}.
   * Clients supporting tags have to handle unknown tags gracefully.
   * <p>
   * Since 3.16.0
   */
  public void setTagSupport(final SymbolTagSupportCapabilities tagSupport) {
    this.tagSupport = tagSupport;
  }

  /**
   * The client supports partial workspace symbols. The client will send the
   * request {@code workspaceSymbol/resolve} to the server to resolve additional
   * properties.
   * <p>
   * Since 3.17.0
   */
  public WorkspaceSymbolResolveSupportCapabilities getResolveSupport() {
    return this.resolveSupport;
  }

  /**
   * The client supports partial workspace symbols. The client will send the
   * request {@code workspaceSymbol/resolve} to the server to resolve additional
   * properties.
   * <p>
   * Since 3.17.0
   */
  public void setResolveSupport(final WorkspaceSymbolResolveSupportCapabilities resolveSupport) {
    this.resolveSupport = resolveSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("symbolKind", this.symbolKind);
    b.add("tagSupport", this.tagSupport);
    b.add("resolveSupport", this.resolveSupport);
    b.add("dynamicRegistration", getDynamicRegistration());
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
    SymbolCapabilities other = (SymbolCapabilities) obj;
    if (this.symbolKind == null) {
      if (other.symbolKind != null)
        return false;
    } else if (!this.symbolKind.equals(other.symbolKind))
      return false;
    if (this.tagSupport == null) {
      if (other.tagSupport != null)
        return false;
    } else if (!this.tagSupport.equals(other.tagSupport))
      return false;
    if (this.resolveSupport == null) {
      if (other.resolveSupport != null)
        return false;
    } else if (!this.resolveSupport.equals(other.resolveSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.symbolKind== null) ? 0 : this.symbolKind.hashCode());
    result = prime * result + ((this.tagSupport== null) ? 0 : this.tagSupport.hashCode());
    return prime * result + ((this.resolveSupport== null) ? 0 : this.resolveSupport.hashCode());
  }
}
