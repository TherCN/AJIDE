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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Provide inline value through a variable lookup.
 * <p>
 * If only a range is specified, the variable name will be extracted from
 * the underlying document.
 * <p>
 * An optional variable name can be used to override the extracted name.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValueVariableLookup {
  /**
   * The document range for which the inline value applies.
   * The range is used to extract the variable name from the underlying
   * document.
   */
  @NonNull
  private Range range;

  /**
   * If specified the name of the variable to look up.
   */
  private String variableName;

  /**
   * How to perform the lookup.
   */
  private boolean caseSensitiveLookup;

  public InlineValueVariableLookup() {
  }

  public InlineValueVariableLookup(@NonNull final Range range, final boolean caseSensitiveLookup) {
    this.range = Preconditions.<Range>checkNotNull(range, "range");
    this.caseSensitiveLookup = (Preconditions.<Boolean>checkNotNull(Boolean.valueOf(caseSensitiveLookup), "caseSensitiveLookup")).booleanValue();
  }

  public InlineValueVariableLookup(@NonNull final Range range, final boolean caseSensitiveLookup, final String variableName) {
    this(range, caseSensitiveLookup);
    this.variableName = Preconditions.<String>checkNotNull(variableName, "variableName");
  }

  /**
   * The document range for which the inline value applies.
   * The range is used to extract the variable name from the underlying
   * document.
   */
  @NonNull
  public Range getRange() {
    return this.range;
  }

  /**
   * The document range for which the inline value applies.
   * The range is used to extract the variable name from the underlying
   * document.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }

  /**
   * If specified the name of the variable to look up.
   */
  public String getVariableName() {
    return this.variableName;
  }

  /**
   * If specified the name of the variable to look up.
   */
  public void setVariableName(final String variableName) {
    this.variableName = variableName;
  }

  /**
   * How to perform the lookup.
   */
  public boolean isCaseSensitiveLookup() {
    return this.caseSensitiveLookup;
  }

  /**
   * How to perform the lookup.
   */
  public void setCaseSensitiveLookup(final boolean caseSensitiveLookup) {
    this.caseSensitiveLookup = caseSensitiveLookup;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("range", this.range);
    b.add("variableName", this.variableName);
    b.add("caseSensitiveLookup", this.caseSensitiveLookup);
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
    InlineValueVariableLookup other = (InlineValueVariableLookup) obj;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.variableName == null) {
      if (other.variableName != null)
        return false;
    } else if (!this.variableName.equals(other.variableName))
      return false;
    if (other.caseSensitiveLookup != this.caseSensitiveLookup)
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    result = prime * result + ((this.variableName== null) ? 0 : this.variableName.hashCode());
    return prime * result + (this.caseSensitiveLookup ? 1231 : 1237);
  }
}
