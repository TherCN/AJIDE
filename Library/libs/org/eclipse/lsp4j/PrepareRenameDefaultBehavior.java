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
 * One of the result types of the `textDocument/prepareRename` request.
 * Indicates that the client should use its default behavior to compute the rename range.
 * <p>
 * Since 3.16.0
 */
@SuppressWarnings("all")
public class PrepareRenameDefaultBehavior {
  /**
   * Indicates that the client should use its default behavior to compute the rename range.
   */
  private boolean defaultBehavior;

  public PrepareRenameDefaultBehavior() {
  }

  public PrepareRenameDefaultBehavior(final boolean defaultBehavior) {
    this.defaultBehavior = defaultBehavior;
  }

  /**
   * Indicates that the client should use its default behavior to compute the rename range.
   */
  public boolean isDefaultBehavior() {
    return this.defaultBehavior;
  }

  /**
   * Indicates that the client should use its default behavior to compute the rename range.
   */
  public void setDefaultBehavior(final boolean defaultBehavior) {
    this.defaultBehavior = defaultBehavior;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("defaultBehavior", this.defaultBehavior);
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
    PrepareRenameDefaultBehavior other = (PrepareRenameDefaultBehavior) obj;
    if (other.defaultBehavior != this.defaultBehavior)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + (this.defaultBehavior ? 1231 : 1237);
  }
}
