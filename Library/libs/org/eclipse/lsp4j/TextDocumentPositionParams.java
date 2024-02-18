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
 * A parameter literal used in requests to pass a text document and a position inside that document.
 */
@SuppressWarnings("all")
public class TextDocumentPositionParams {
  /**
   * The text document.
   */
  @NonNull
  private TextDocumentIdentifier textDocument;

  /**
   * The position inside the text document.
   */
  @NonNull
  private Position position;

  public TextDocumentPositionParams() {
  }

  public TextDocumentPositionParams(@NonNull final TextDocumentIdentifier textDocument, @NonNull final Position position) {
    this.textDocument = Preconditions.<TextDocumentIdentifier>checkNotNull(textDocument, "textDocument");
    this.position = Preconditions.<Position>checkNotNull(position, "position");
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
   * The position inside the text document.
   */
  @NonNull
  public Position getPosition() {
    return this.position;
  }

  /**
   * The position inside the text document.
   */
  public void setPosition(@NonNull final Position position) {
    this.position = Preconditions.checkNotNull(position, "position");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("textDocument", this.textDocument);
    b.add("position", this.position);
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
    TextDocumentPositionParams other = (TextDocumentPositionParams) obj;
    if (this.textDocument == null) {
      if (other.textDocument != null)
        return false;
    } else if (!this.textDocument.equals(other.textDocument))
      return false;
    if (this.position == null) {
      if (other.position != null)
        return false;
    } else if (!this.position.equals(other.position))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.textDocument== null) ? 0 : this.textDocument.hashCode());
    return prime * result + ((this.position== null) ? 0 : this.position.hashCode());
  }
}
