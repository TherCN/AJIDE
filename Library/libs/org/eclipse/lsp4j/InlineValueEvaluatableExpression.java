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
 * Provide an inline value through an expression evaluation.
 * <p>
 * If only a range is specified, the expression will be extracted from the
 * underlying document.
 * <p>
 * An optional expression can be used to override the extracted expression.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValueEvaluatableExpression {
  /**
   * The document range for which the inline value applies.
   * The range is used to extract the evaluatable expression from the
   * underlying document.
   */
  @NonNull
  private Range range;

  /**
   * If specified the name of the variable to look up.
   */
  private String expression;

  public InlineValueEvaluatableExpression() {
  }

  public InlineValueEvaluatableExpression(@NonNull final Range range) {
    this.range = Preconditions.<Range>checkNotNull(range, "range");
  }

  public InlineValueEvaluatableExpression(@NonNull final Range range, final String expression) {
    this(range);
    this.expression = Preconditions.<String>checkNotNull(expression, "expression");
  }

  /**
   * The document range for which the inline value applies.
   * The range is used to extract the evaluatable expression from the
   * underlying document.
   */
  @NonNull
  public Range getRange() {
    return this.range;
  }

  /**
   * The document range for which the inline value applies.
   * The range is used to extract the evaluatable expression from the
   * underlying document.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }

  /**
   * If specified the name of the variable to look up.
   */
  public String getExpression() {
    return this.expression;
  }

  /**
   * If specified the name of the variable to look up.
   */
  public void setExpression(final String expression) {
    this.expression = expression;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("range", this.range);
    b.add("expression", this.expression);
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
    InlineValueEvaluatableExpression other = (InlineValueEvaluatableExpression) obj;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.expression == null) {
      if (other.expression != null)
        return false;
    } else if (!this.expression.equals(other.expression))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    return prime * result + ((this.expression== null) ? 0 : this.expression.hashCode());
  }
}
