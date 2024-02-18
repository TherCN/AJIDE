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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * The document change notification is sent from the client to the server to signal changes to a text document.
 */
@SuppressWarnings("all")
public class DidChangeTextDocumentParams {
  /**
   * The document that did change. The version number points to the version after all provided content changes have
   * been applied.
   */
  @NonNull
  private VersionedTextDocumentIdentifier textDocument;

  /**
   * The actual content changes.
   */
  @NonNull
  private List<TextDocumentContentChangeEvent> contentChanges = new ArrayList<TextDocumentContentChangeEvent>();

  public DidChangeTextDocumentParams() {
  }

  public DidChangeTextDocumentParams(@NonNull final VersionedTextDocumentIdentifier textDocument, @NonNull final List<TextDocumentContentChangeEvent> contentChanges) {
    this.textDocument = Preconditions.<VersionedTextDocumentIdentifier>checkNotNull(textDocument, "textDocument");
    this.contentChanges = Preconditions.<List<TextDocumentContentChangeEvent>>checkNotNull(contentChanges, "contentChanges");
  }

  /**
   * The document that did change. The version number points to the version after all provided content changes have
   * been applied.
   */
  @NonNull
  public VersionedTextDocumentIdentifier getTextDocument() {
    return this.textDocument;
  }

  /**
   * The document that did change. The version number points to the version after all provided content changes have
   * been applied.
   */
  public void setTextDocument(@NonNull final VersionedTextDocumentIdentifier textDocument) {
    this.textDocument = Preconditions.checkNotNull(textDocument, "textDocument");
  }

  /**
   * The actual content changes.
   */
  @NonNull
  public List<TextDocumentContentChangeEvent> getContentChanges() {
    return this.contentChanges;
  }

  /**
   * The actual content changes.
   */
  public void setContentChanges(@NonNull final List<TextDocumentContentChangeEvent> contentChanges) {
    this.contentChanges = Preconditions.checkNotNull(contentChanges, "contentChanges");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("textDocument", this.textDocument);
    b.add("contentChanges", this.contentChanges);
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
    DidChangeTextDocumentParams other = (DidChangeTextDocumentParams) obj;
    if (this.textDocument == null) {
      if (other.textDocument != null)
        return false;
    } else if (!this.textDocument.equals(other.textDocument))
      return false;
    if (this.contentChanges == null) {
      if (other.contentChanges != null)
        return false;
    } else if (!this.contentChanges.equals(other.contentChanges))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.textDocument== null) ? 0 : this.textDocument.hashCode());
    return prime * result + ((this.contentChanges== null) ? 0 : this.contentChanges.hashCode());
  }
}
