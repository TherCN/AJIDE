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
 * Capabilities specific to the {@code textDocument/inlayHint} request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlayHintCapabilities extends DynamicRegistrationCapabilities {
  /**
   * Indicates which properties a client can resolve lazily on a inlay hint.
   */
  private InlayHintResolveSupportCapabilities resolveSupport;

  public InlayHintCapabilities() {
  }

  public InlayHintCapabilities(final Boolean dynamicRegistration) {
    super(dynamicRegistration);
  }

  /**
   * Indicates which properties a client can resolve lazily on a inlay hint.
   */
  public InlayHintResolveSupportCapabilities getResolveSupport() {
    return this.resolveSupport;
  }

  /**
   * Indicates which properties a client can resolve lazily on a inlay hint.
   */
  public void setResolveSupport(final InlayHintResolveSupportCapabilities resolveSupport) {
    this.resolveSupport = resolveSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
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
    InlayHintCapabilities other = (InlayHintCapabilities) obj;
    if (this.resolveSupport == null) {
      if (other.resolveSupport != null)
        return false;
    } else if (!this.resolveSupport.equals(other.resolveSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.resolveSupport== null) ? 0 : this.resolveSupport.hashCode());
  }
}
