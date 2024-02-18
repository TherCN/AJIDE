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
 * Provide inline value as text.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValueText {
  /**
   * The document range for which the inline value applies.
   */
  @NonNull
  private Range range;

  /**
   * The text of the inline value.
   */
  @NonNull
  private String text;

  public InlineValueText() {
  }

  public InlineValueText(@NonNull final Range range, @NonNull final String text) {
    this.range = Preconditions.<Range>checkNotNull(range, "range");
    this.text = Preconditions.<String>checkNotNull(text, "text");
  }

  /**
   * The document range for which the inline value applies.
   */
  @NonNull
  public Range getRange() {
    return this.range;
  }

  /**
   * The document range for which the inline value applies.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }

  /**
   * The text of the inline value.
   */
  @NonNull
  public String getText() {
    return this.text;
  }

  /**
   * The text of the inline value.
   */
  public void setText(@NonNull final String text) {
    this.text = Preconditions.checkNotNull(text, "text");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("range", this.range);
    b.add("text", this.text);
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
    InlineValueText other = (InlineValueText) obj;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.text == null) {
      if (other.text != null)
        return false;
    } else if (!this.text.equals(other.text))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    return prime * result + ((this.text== null) ? 0 : this.text.hashCode());
  }
}
