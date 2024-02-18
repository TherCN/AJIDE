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

@SuppressWarnings("all")
public class FileSystemWatcher {
  /**
   * The glob pattern to watch. Either a string pattern relative to the base path or a relative pattern.
   */
  @NonNull
  private Either<String, RelativePattern> globPattern;

  /**
   * The kind of events of interest. If omitted it defaults
   * to {@link WatchKind#Create} | {@link WatchKind#Change} | {@link WatchKind#Delete}
   * which is {@code 7}.
   */
  private Integer kind;

  public FileSystemWatcher() {
  }

  public FileSystemWatcher(@NonNull final Either<String, RelativePattern> globPattern) {
    this.globPattern = Preconditions.<Either<String, RelativePattern>>checkNotNull(globPattern, "globPattern");
  }

  public FileSystemWatcher(@NonNull final Either<String, RelativePattern> globPattern, final Integer kind) {
    this(globPattern);
    this.kind = kind;
  }

  /**
   * The glob pattern to watch. Either a string pattern relative to the base path or a relative pattern.
   */
  @NonNull
  public Either<String, RelativePattern> getGlobPattern() {
    return this.globPattern;
  }

  /**
   * The glob pattern to watch. Either a string pattern relative to the base path or a relative pattern.
   */
  public void setGlobPattern(@NonNull final Either<String, RelativePattern> globPattern) {
    this.globPattern = Preconditions.checkNotNull(globPattern, "globPattern");
  }

  public void setGlobPattern(final String globPattern) {
    if (globPattern == null) {
      Preconditions.checkNotNull(globPattern, "globPattern");
      this.globPattern = null;
      return;
    }
    this.globPattern = Either.forLeft(globPattern);
  }

  public void setGlobPattern(final RelativePattern globPattern) {
    if (globPattern == null) {
      Preconditions.checkNotNull(globPattern, "globPattern");
      this.globPattern = null;
      return;
    }
    this.globPattern = Either.forRight(globPattern);
  }

  /**
   * The kind of events of interest. If omitted it defaults
   * to {@link WatchKind#Create} | {@link WatchKind#Change} | {@link WatchKind#Delete}
   * which is {@code 7}.
   */
  public Integer getKind() {
    return this.kind;
  }

  /**
   * The kind of events of interest. If omitted it defaults
   * to {@link WatchKind#Create} | {@link WatchKind#Change} | {@link WatchKind#Delete}
   * which is {@code 7}.
   */
  public void setKind(final Integer kind) {
    this.kind = kind;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("globPattern", this.globPattern);
    b.add("kind", this.kind);
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
    FileSystemWatcher other = (FileSystemWatcher) obj;
    if (this.globPattern == null) {
      if (other.globPattern != null)
        return false;
    } else if (!this.globPattern.equals(other.globPattern))
      return false;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.globPattern== null) ? 0 : this.globPattern.hashCode());
    return prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
  }
}
