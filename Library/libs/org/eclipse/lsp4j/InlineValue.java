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

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.messages.Either3;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Inline value information can be provided by different means:
 * - directly as a text value ({@link InlineValueText}).
 * - as a name to use for a variable lookup ({@link InlineValueVariableLookup})
 * - as an evaluatable expression ({@link InlineValueEvaluatableExpression})
 * The InlineValue types combines all inline value types into one type.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValue extends Either3<InlineValueText, InlineValueVariableLookup, InlineValueEvaluatableExpression> {
  public InlineValue(@NonNull final InlineValueText inlineValueText) {
    super(Preconditions.<InlineValueText>checkNotNull(inlineValueText, "inlineValueText"), null);
  }

  public InlineValue(@NonNull final InlineValueVariableLookup inlineValueVariableLookup) {
    super(null, Either.<InlineValueVariableLookup, InlineValueEvaluatableExpression>forLeft(Preconditions.<InlineValueVariableLookup>checkNotNull(inlineValueVariableLookup, "inlineValueVariableLookup")));
  }

  public InlineValue(@NonNull final InlineValueEvaluatableExpression inlineValueEvaluatableExpression) {
    super(null, Either.<InlineValueVariableLookup, InlineValueEvaluatableExpression>forRight(Preconditions.<InlineValueEvaluatableExpression>checkNotNull(inlineValueEvaluatableExpression, "inlineValueEvaluatableExpression")));
  }

  public InlineValueText getInlineValueText() {
    return super.getFirst();
  }

  public boolean isInlineValueText() {
    return super.isFirst();
  }

  public InlineValueVariableLookup getInlineValueVariableLookup() {
    return super.getSecond();
  }

  public boolean isInlineValueVariableLookup() {
    return super.isSecond();
  }

  public InlineValueEvaluatableExpression getInlineValueEvaluatableExpression() {
    return super.getThird();
  }

  public boolean isInlineValueEvaluatableExpression() {
    return super.isThird();
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("left", getLeft());
    b.add("right", getRight());
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
    return true;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
