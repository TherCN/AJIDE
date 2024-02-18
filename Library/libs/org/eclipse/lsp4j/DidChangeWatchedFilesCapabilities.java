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
 * Capabilities specific to the `workspace/didChangeWatchedFiles` notification.
 */
@SuppressWarnings("all")
public class DidChangeWatchedFilesCapabilities extends DynamicRegistrationCapabilities {
  /**
   * Whether the client has support for relative patterns
   * or not.
   * <p>
   * Since 3.17.0
   */
  private Boolean relativePatternSupport;

  public DidChangeWatchedFilesCapabilities() {
  }

  public DidChangeWatchedFilesCapabilities(final Boolean dynamicRegistration) {
    super(dynamicRegistration);
  }

  /**
   * Whether the client has support for relative patterns
   * or not.
   * <p>
   * Since 3.17.0
   */
  public Boolean getRelativePatternSupport() {
    return this.relativePatternSupport;
  }

  /**
   * Whether the client has support for relative patterns
   * or not.
   * <p>
   * Since 3.17.0
   */
  public void setRelativePatternSupport(final Boolean relativePatternSupport) {
    this.relativePatternSupport = relativePatternSupport;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("relativePatternSupport", this.relativePatternSupport);
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
    DidChangeWatchedFilesCapabilities other = (DidChangeWatchedFilesCapabilities) obj;
    if (this.relativePatternSupport == null) {
      if (other.relativePatternSupport != null)
        return false;
    } else if (!this.relativePatternSupport.equals(other.relativePatternSupport))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.relativePatternSupport== null) ? 0 : this.relativePatternSupport.hashCode());
  }
}
