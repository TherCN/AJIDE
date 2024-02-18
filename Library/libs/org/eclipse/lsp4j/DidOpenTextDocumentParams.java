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
 * The document open notification is sent from the client to the server to signal newly opened text documents.
 * The document's truth is now managed by the client and the server must not try to read the document's truth using
 * the document's uri.
 */
@SuppressWarnings("all")
public class DidOpenTextDocumentParams {
  /**
   * The document that was opened.
   */
  @NonNull
  private TextDocumentItem textDocument;

  public DidOpenTextDocumentParams() {
  }

  public DidOpenTextDocumentParams(@NonNull final TextDocumentItem textDocument) {
    this.textDocument = Preconditions.<TextDocumentItem>checkNotNull(textDocument, "textDocument");
  }

  /**
   * The document that was opened.
   */
  @NonNull
  public TextDocumentItem getTextDocument() {
    return this.textDocument;
  }

  /**
   * The document that was opened.
   */
  public void setTextDocument(@NonNull final TextDocumentItem textDocument) {
    this.textDocument = Preconditions.checkNotNull(textDocument, "textDocument");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("textDocument", this.textDocument);
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
    DidOpenTextDocumentParams other = (DidOpenTextDocumentParams) obj;
    if (this.textDocument == null) {
      if (other.textDocument != null)
        return false;
    } else if (!this.textDocument.equals(other.textDocument))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((this.textDocument== null) ? 0 : this.textDocument.hashCode());
  }
}
