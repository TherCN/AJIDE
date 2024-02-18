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
 * Specific options for the folding range.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class FoldingRangeSupportCapabilities {
  /**
   * If set, the client signals that it supports setting {@link FoldingRange#collapsedText} on
   * folding ranges to display custom labels instead of the default text.
   */
  private Boolean collapsedText;

  public FoldingRangeSupportCapabilities() {
  }

  public FoldingRangeSupportCapabilities(final Boolean collapsedText) {
    this.collapsedText = collapsedText;
  }

  /**
   * If set, the client signals that it supports setting {@link FoldingRange#collapsedText} on
   * folding ranges to display custom labels instead of the default text.
   */
  public Boolean getCollapsedText() {
    return this.collapsedText;
  }

  /**
   * If set, the client signals that it supports setting {@link FoldingRange#collapsedText} on
   * folding ranges to display custom labels instead of the default text.
   */
  public void setCollapsedText(final Boolean collapsedText) {
    this.collapsedText = collapsedText;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("collapsedText", this.collapsedText);
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
    FoldingRangeSupportCapabilities other = (FoldingRangeSupportCapabilities) obj;
    if (this.collapsedText == null) {
      if (other.collapsedText != null)
        return false;
    } else if (!this.collapsedText.equals(other.collapsedText))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.collapsedText== null) ? 0 : this.collapsedText.hashCode());
  }
}
