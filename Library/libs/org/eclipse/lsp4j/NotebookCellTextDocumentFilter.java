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
 * A notebook cell text document filter denotes a cell text
 * document by different properties.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookCellTextDocumentFilter {
  /**
   * A filter that matches against the notebook
   * containing the notebook cell. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  @NonNull
  private Either<String, NotebookDocumentFilter> notebook;

  /**
   * A language id like `python`.
   * <p>
   * Will be matched against the language id of the
   * notebook cell document. '*' matches every language.
   */
  private String language;

  public NotebookCellTextDocumentFilter() {
  }

  public NotebookCellTextDocumentFilter(@NonNull final Either<String, NotebookDocumentFilter> notebook) {
    this.notebook = Preconditions.<Either<String, NotebookDocumentFilter>>checkNotNull(notebook, "notebook");
  }

  public NotebookCellTextDocumentFilter(@NonNull final Either<String, NotebookDocumentFilter> notebook, final String language) {
    this(notebook);
    this.language = language;
  }

  /**
   * A filter that matches against the notebook
   * containing the notebook cell. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  @NonNull
  public Either<String, NotebookDocumentFilter> getNotebook() {
    return this.notebook;
  }

  /**
   * A filter that matches against the notebook
   * containing the notebook cell. If a string
   * value is provided it matches against the
   * notebook type. '*' matches every notebook.
   */
  public void setNotebook(@NonNull final Either<String, NotebookDocumentFilter> notebook) {
    this.notebook = Preconditions.checkNotNull(notebook, "notebook");
  }

  public void setNotebook(final String notebook) {
    if (notebook == null) {
      Preconditions.checkNotNull(notebook, "notebook");
      this.notebook = null;
      return;
    }
    this.notebook = Either.forLeft(notebook);
  }

  public void setNotebook(final NotebookDocumentFilter notebook) {
    if (notebook == null) {
      Preconditions.checkNotNull(notebook, "notebook");
      this.notebook = null;
      return;
    }
    this.notebook = Either.forRight(notebook);
  }

  /**
   * A language id like `python`.
   * <p>
   * Will be matched against the language id of the
   * notebook cell document. '*' matches every language.
   */
  public String getLanguage() {
    return this.language;
  }

  /**
   * A language id like `python`.
   * <p>
   * Will be matched against the language id of the
   * notebook cell document. '*' matches every language.
   */
  public void setLanguage(final String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("notebook", this.notebook);
    b.add("language", this.language);
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
    NotebookCellTextDocumentFilter other = (NotebookCellTextDocumentFilter) obj;
    if (this.notebook == null) {
      if (other.notebook != null)
        return false;
    } else if (!this.notebook.equals(other.notebook))
      return false;
    if (this.language == null) {
      if (other.language != null)
        return false;
    } else if (!this.language.equals(other.language))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.notebook== null) ? 0 : this.notebook.hashCode());
    return prime * result + ((this.language== null) ? 0 : this.language.hashCode());
  }
}
