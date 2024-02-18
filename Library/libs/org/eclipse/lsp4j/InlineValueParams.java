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
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A parameter literal used in inline value requests.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValueParams implements WorkDoneProgressParams {
  /**
   * An optional token that a server can use to report work done progress.
   */
  private Either<String, Integer> workDoneToken;

  /**
   * The text document.
   */
  @NonNull
  private TextDocumentIdentifier textDocument;

  /**
   * The visible document range for which inlay hints should be computed.
   */
  @NonNull
  private Range range;

  /**
   * Additional information about the context in which inline values were
   * requested.
   */
  @NonNull
  private InlineValueContext context;

  public InlineValueParams() {
  }

  public InlineValueParams(@NonNull final TextDocumentIdentifier textDocument, @NonNull final Range range, @NonNull final InlineValueContext context) {
    this.textDocument = Preconditions.<TextDocumentIdentifier>checkNotNull(textDocument, "textDocument");
    this.range = Preconditions.<Range>checkNotNull(range, "range");
    this.context = Preconditions.<InlineValueContext>checkNotNull(context, "context");
  }

  /**
   * An optional token that a server can use to report work done progress.
   */
  @Override
  public Either<String, Integer> getWorkDoneToken() {
    return this.workDoneToken;
  }

  /**
   * An optional token that a server can use to report work done progress.
   */
  public void setWorkDoneToken(final Either<String, Integer> workDoneToken) {
    this.workDoneToken = workDoneToken;
  }

  public void setWorkDoneToken(final String workDoneToken) {
    if (workDoneToken == null) {
      this.workDoneToken = null;
      return;
    }
    this.workDoneToken = Either.forLeft(workDoneToken);
  }

  public void setWorkDoneToken(final Integer workDoneToken) {
    if (workDoneToken == null) {
      this.workDoneToken = null;
      return;
    }
    this.workDoneToken = Either.forRight(workDoneToken);
  }

  /**
   * The text document.
   */
  @NonNull
  public TextDocumentIdentifier getTextDocument() {
    return this.textDocument;
  }

  /**
   * The text document.
   */
  public void setTextDocument(@NonNull final TextDocumentIdentifier textDocument) {
    this.textDocument = Preconditions.checkNotNull(textDocument, "textDocument");
  }

  /**
   * The visible document range for which inlay hints should be computed.
   */
  @NonNull
  public Range getRange() {
    return this.range;
  }

  /**
   * The visible document range for which inlay hints should be computed.
   */
  public void setRange(@NonNull final Range range) {
    this.range = Preconditions.checkNotNull(range, "range");
  }

  /**
   * Additional information about the context in which inline values were
   * requested.
   */
  @NonNull
  public InlineValueContext getContext() {
    return this.context;
  }

  /**
   * Additional information about the context in which inline values were
   * requested.
   */
  public void setContext(@NonNull final InlineValueContext context) {
    this.context = Preconditions.checkNotNull(context, "context");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("workDoneToken", this.workDoneToken);
    b.add("textDocument", this.textDocument);
    b.add("range", this.range);
    b.add("context", this.context);
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
    InlineValueParams other = (InlineValueParams) obj;
    if (this.workDoneToken == null) {
      if (other.workDoneToken != null)
        return false;
    } else if (!this.workDoneToken.equals(other.workDoneToken))
      return false;
    if (this.textDocument == null) {
      if (other.textDocument != null)
        return false;
    } else if (!this.textDocument.equals(other.textDocument))
      return false;
    if (this.range == null) {
      if (other.range != null)
        return false;
    } else if (!this.range.equals(other.range))
      return false;
    if (this.context == null) {
      if (other.context != null)
        return false;
    } else if (!this.context.equals(other.context))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.workDoneToken== null) ? 0 : this.workDoneToken.hashCode());
    result = prime * result + ((this.textDocument== null) ? 0 : this.textDocument.hashCode());
    result = prime * result + ((this.range== null) ? 0 : this.range.hashCode());
    return prime * result + ((this.context== null) ? 0 : this.context.hashCode());
  }
}
