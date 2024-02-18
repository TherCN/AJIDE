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
 * Capabilities specific to `textDocument/foldingRange` requests.
 * <p>
 * Since 3.10.0
 */
@SuppressWarnings("all")
public class FoldingRangeCapabilities extends DynamicRegistrationCapabilities {
  /**
   * The maximum number of folding ranges that the client prefers to receive per document. The value serves as a
   * hint, servers are free to follow the limit.
   */
  private Integer rangeLimit;

  /**
   * If set, the client signals that it only supports folding complete lines. If set, client will
   * ignore specified {@link FoldingRange#startCharacter} and {@link FoldingRange#endCharacter} properties.
   */
  private Boolean lineFoldingOnly;

  /**
   * Specific options for the folding range kind.
   * <p>
   * Since 3.17.0
   */
  private FoldingRangeKindSupportCapabilities foldingRangeKind;

  /**
   * Specific options for the folding range.
   * <p>
   * Since 3.17.0
   */
  private FoldingRangeSupportCapabilities foldingRange;

  public FoldingRangeCapabilities() {
  }

  /**
   * The maximum number of folding ranges that the client prefers to receive per document. The value serves as a
   * hint, servers are free to follow the limit.
   */
  public Integer getRangeLimit() {
    return this.rangeLimit;
  }

  /**
   * The maximum number of folding ranges that the client prefers to receive per document. The value serves as a
   * hint, servers are free to follow the limit.
   */
  public void setRangeLimit(final Integer rangeLimit) {
    this.rangeLimit = rangeLimit;
  }

  /**
   * If set, the client signals that it only supports folding complete lines. If set, client will
   * ignore specified {@link FoldingRange#startCharacter} and {@link FoldingRange#endCharacter} properties.
   */
  public Boolean getLineFoldingOnly() {
    return this.lineFoldingOnly;
  }

  /**
   * If set, the client signals that it only supports folding complete lines. If set, client will
   * ignore specified {@link FoldingRange#startCharacter} and {@link FoldingRange#endCharacter} properties.
   */
  public void setLineFoldingOnly(final Boolean lineFoldingOnly) {
    this.lineFoldingOnly = lineFoldingOnly;
  }

  /**
   * Specific options for the folding range kind.
   * <p>
   * Since 3.17.0
   */
  public FoldingRangeKindSupportCapabilities getFoldingRangeKind() {
    return this.foldingRangeKind;
  }

  /**
   * Specific options for the folding range kind.
   * <p>
   * Since 3.17.0
   */
  public void setFoldingRangeKind(final FoldingRangeKindSupportCapabilities foldingRangeKind) {
    this.foldingRangeKind = foldingRangeKind;
  }

  /**
   * Specific options for the folding range.
   * <p>
   * Since 3.17.0
   */
  public FoldingRangeSupportCapabilities getFoldingRange() {
    return this.foldingRange;
  }

  /**
   * Specific options for the folding range.
   * <p>
   * Since 3.17.0
   */
  public void setFoldingRange(final FoldingRangeSupportCapabilities foldingRange) {
    this.foldingRange = foldingRange;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("rangeLimit", this.rangeLimit);
    b.add("lineFoldingOnly", this.lineFoldingOnly);
    b.add("foldingRangeKind", this.foldingRangeKind);
    b.add("foldingRange", this.foldingRange);
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
    FoldingRangeCapabilities other = (FoldingRangeCapabilities) obj;
    if (this.rangeLimit == null) {
      if (other.rangeLimit != null)
        return false;
    } else if (!this.rangeLimit.equals(other.rangeLimit))
      return false;
    if (this.lineFoldingOnly == null) {
      if (other.lineFoldingOnly != null)
        return false;
    } else if (!this.lineFoldingOnly.equals(other.lineFoldingOnly))
      return false;
    if (this.foldingRangeKind == null) {
      if (other.foldingRangeKind != null)
        return false;
    } else if (!this.foldingRangeKind.equals(other.foldingRangeKind))
      return false;
    if (this.foldingRange == null) {
      if (other.foldingRange != null)
        return false;
    } else if (!this.foldingRange.equals(other.foldingRange))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.rangeLimit== null) ? 0 : this.rangeLimit.hashCode());
    result = prime * result + ((this.lineFoldingOnly== null) ? 0 : this.lineFoldingOnly.hashCode());
    result = prime * result + ((this.foldingRangeKind== null) ? 0 : this.foldingRangeKind.hashCode());
    return prime * result + ((this.foldingRange== null) ? 0 : this.foldingRange.hashCode());
  }
}
